pipeline {
    agent any
    
    tools {
        maven 'maven-3'  // Configure in Jenkins: Manage Jenkins → Tools
        jdk 'jdk-11'     // Configure in Jenkins: Manage Jenkins → Tools
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Code checked out successfully'
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compiling...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Run Tests') {
            steps {
                echo 'Executing Selenium tests...'
                // Run all tests
                sh 'mvn test'
                
                // Or run specific test suite
                // sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }
        
        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                // Publish HTML reports
                publishHTML([
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'Test Report'
                ])
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
