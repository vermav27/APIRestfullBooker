pipeline {
    agent any

    stages {
        stage('Run API Tests') {
            steps {
                bat 'mvn clean test "-DsuiteXmlFile=testng_CRUD.xml"'
            }
        }
    }

    post {
        success {
            echo "BUILD SUCCESS for ${env.BRANCH_NAME}"
        }
        failure {
            echo "BUILD FAILED for ${env.BRANCH_NAME}"
        }
    }
}
