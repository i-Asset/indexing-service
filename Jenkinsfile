#!/usr/bin/env groovy

node('nimble-jenkins-slave') {

    // -----------------------------------------------
    // --------------- Staging Branch ----------------
    // -----------------------------------------------
    if (env.BRANCH_NAME == 'staging') {

        stage('Clone and Update') {
            git(url: 'https://github.com/i-Asset/indexing-service', branch: env.BRANCH_NAME)
        }
        stage('Build Dependencies') {
            sh 'rm -rf common'
            sh 'git clone https://github.com/i-Asset/common.git'
            dir('common') {
                sh 'git checkout ' + env.BRANCH_NAME
                sh 'mvn clean install'
            }

            sh 'rm -rf solr-model'
            sh 'git clone https://github.com/i-Asset/solr-model.git'
            dir('common') {
                sh 'git checkout ' + env.BRANCH_NAME
                sh 'mvn clean install'
            }

            sh 'rm -rf solr-indexing'
            sh 'git clone https://github.com/i-Asset/solr-indexing.git'
            dir('common') {
                sh 'git checkout ' + env.BRANCH_NAME
                sh 'mvn clean install -DskipTests'
            }
        }
//        stage('Run Tests') {
//            sh 'mvn clean test'
//        }

        stage('Build Java') {
            sh 'mvn clean install -DskipTests'
        }

        stage('Build Docker') {
            sh 'mvn docker:build -Ddocker.image.tag=staging'
        }

        stage('Push Docker') {
            sh 'docker push iassetplatform/indexing-service:staging'
        }

        stage('Deploy') {
            sh 'ssh staging "cd /srv/docker-setup/staging/ && ./run-staging.sh restart-single indexing-service"'
        }
    }

    // -----------------------------------------------
    // ---------------- Master Branch ----------------
    // -----------------------------------------------
    if (env.BRANCH_NAME == 'master') {

        stage('Clone and Update') {
            git(url: 'https://github.com/nimble-platform/indexing-service.git', branch: env.BRANCH_NAME)
        }

        stage('Build Dependencies') {
            sh 'rm -rf common'
            sh 'git clone https://github.com/nimble-platform/common'
            dir('common') {
                sh 'git checkout ' + env.BRANCH_NAME
                sh 'mvn clean install'
            }
        }

//        stage('Run Tests') {
//            sh 'mvn clean test'
//        }

        stage('Build Java') {
            sh 'mvn clean install -DskipTests'
        }

        stage('Build Docker') {
            sh 'mvn docker:build -Ddocker.image.tag=latest'
        }
    }

    // -----------------------------------------------
    // ---------------- Release Tags -----------------
    // -----------------------------------------------
    if( env.TAG_NAME ==~ /^\d+.\d+.\d+$/) {

        stage('Clone and Update') {
            git(url: 'https://github.com/nimble-platform/indexing-service.git', branch: env.BRANCH_NAME)
        }

        stage('Build Dependencies') {
            sh 'rm -rf common'
            sh 'git clone https://github.com/nimble-platform/common'
            dir('common') {
                sh 'git checkout ' + env.BRANCH_NAME
                sh 'mvn clean install'
            }
        }

        stage('Set version') {
            sh 'mvn org.codehaus.mojo:versions-maven-plugin:2.1:set -DnewVersion=' + env.TAG_NAME
        }

//        stage('Run Tests') {
//            sh 'mvn clean test'
//        }

        stage('Build Java') {
            sh 'mvn clean install -DskipTests'
        }

        stage('Build Docker') {
            sh 'mvn docker:build'
        }

        stage('Push Docker') {
            sh 'docker push nimbleplatform/identity-service:' + env.TAG_NAME
            sh 'docker push nimbleplatform/identity-service:latest'
        }

        stage('Deploy MVP') {
            sh 'ssh nimble "cd /data/deployment_setup/prod/ && sudo ./run-prod.sh restart-single indexing-service"'
        }

        stage('Deploy FMP') {
            sh 'ssh fmp-prod "cd /srv/nimble-fmp/ && ./run-fmp-prod.sh restart-single indexing-service"'
        }
    }
}
