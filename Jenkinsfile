pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Run Selenium Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }  
    post {
        always {
            // Archive test results
            junit 'target/surefire-reports/*.xml'
            
            // Clean workspace
            cleanWs()
        }
        success {
            echo 'Tests passed!'
        }
        failure {
            echo 'Tests failed!'
        }
    }
}
