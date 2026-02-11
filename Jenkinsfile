pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo "Building branch: ${env.BRANCH_NAME}"
                checkout scm
            }
        }

        stage('Run API Tests') {
            steps {
                sh 'mvn clean test "-DsuiteXmlFile=testng_CRUD.xml"'
            }
        }
    }

    post {
        success {
            echo "BUILD PASSED for ${env.BRANCH_NAME}"
        }
        failure {
            echo "BUILD FAILED for ${env.BRANCH_NAME}"
        }
    }
}
