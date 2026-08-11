pipeline {

    agent any

    environment {
        PATH = "/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

    stages {

        stage('Environment Check') {
            steps {
                echo '===== ENVIRONMENT CHECK ====='

                sh '''
                    java -version
                    mvn -version
                    allure --version
                '''
            }
        }

        stage('Smoke Test') {
            steps {
                echo '===== SMOKE TEST START ====='

                sh '''
                    mvn clean test -Dtest=runner.SmokeRunner

                    rm -rf allure-results-smoke
                    mkdir -p allure-results-smoke
                    cp -R target/allure-results/. allure-results-smoke/
                '''

                echo '===== SMOKE TEST FINISHED ====='
            }
        }

        stage('Regression Test') {
            steps {
                echo '===== REGRESSION TEST START ====='

                sh '''
                    mvn clean test -Dtest=runner.RegressionRunner

                    rm -rf allure-results-regression
                    mkdir -p allure-results-regression
                    cp -R target/allure-results/. allure-results-regression/
                '''

                echo '===== REGRESSION TEST FINISHED ====='
            }
        }

        stage('E2E Test') {
            steps {
                echo '===== E2E TEST START ====='

                sh '''
                    mvn clean test -Dtest=runner.E2ERunner

                    rm -rf allure-results-e2e
                    mkdir -p allure-results-e2e
                    cp -R target/allure-results/. allure-results-e2e/
                '''

                echo '===== E2E TEST FINISHED ====='
            }
        }

        stage('Merge Allure Results') {
            steps {
                echo '===== MERGING ALLURE RESULTS ====='

                sh '''
                    rm -rf allure-results-all
                    mkdir -p allure-results-all

                    cp -R allure-results-smoke/. allure-results-all/
                    cp -R allure-results-regression/. allure-results-all/
                    cp -R allure-results-e2e/. allure-results-all/

                    echo "===== MERGED RESULTS ====="
                    ls -la allure-results-all
                '''
            }
        }

        stage('Generate Allure HTML') {
            steps {
                echo '===== GENERATING ALLURE HTML REPORT ====='

                sh '''
                    rm -rf allure-report

                    allure generate allure-results-all \
                        --clean \
                        -o allure-report

                    echo "===== ALLURE HTML GENERATED ====="
                    ls -la allure-report
                '''
            }
        }

        stage('Publish HTML Report') {
            steps {
                echo '===== PUBLISHING HTML REPORT ====='

                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'allure-report',
                    reportFiles: 'index.html',
                    reportName: 'Allure Report',
                    reportTitles: 'Smplifai Automation Report'
                ])
            }
        }
    }

    post {

        success {
            echo '========================================'
            echo 'PIPELINE SUCCESS'
            echo 'Smoke + Regression + E2E completed'
            echo 'Allure HTML Report published'
            echo '========================================'
        }

        failure {
            echo '========================================'
            echo 'PIPELINE FAILED'
            echo 'Console Output-u yoxla'
            echo '========================================'
        }

        always {
            archiveArtifacts artifacts: 'allure-report/**/*',
                             allowEmptyArchive: true,
                             fingerprint: true

            echo 'Pipeline finished.'
        }
    }
}
