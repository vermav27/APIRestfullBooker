pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run API Automation Tests') {
            steps {
                bat 'mvn clean test "-DsuiteXmlFile=testng_CRUD.xml"'
            }
        }
    }

    post {
        success {
            echo 'API Automation Pipeline PASSED'
        }
        failure {
            echo 'API Automation Pipeline FAILED'
        }
    }
}
