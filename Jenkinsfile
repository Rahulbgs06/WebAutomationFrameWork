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
                script {
                    // Run Maven tests and capture exit code
                    def testResult = sh(script: 'mvn clean test', returnStatus: true)

                    if (testResult == 0) {
                        env.TEST_STATUS = 'PASS'
                        echo "\033[1;32m✅ TESTS PASSED ✅\033[0m"
                    } else {
                        env.TEST_STATUS = 'FAIL'
                        echo "\033[1;31m❌ TESTS FAILED ❌\033[0m"
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Publishing Extent Report... Test Status: ${env.TEST_STATUS}"

            // Archive JUnit XML results
            junit 'target/surefire-reports/*.xml'

            // Publish Extent HTML report
            publishHTML([
                allowMissing: true,                     // Won't fail if report missing
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',                   // Your actual Extent report folder
                reportFiles: 'ExtentReport.html',       // Your Extent report file
                reportName: 'Selenium Extent Report'
            ])

            // Clean workspace
            cleanWs()
        }

        success {
            echo "\033[1;32mAll stages completed successfully! ✅\033[0m"
        }

        failure {
            echo "\033[1;31mBuild failed! Check test results. ❌\033[0m"
        }
    }
}
