pipeline {
    agent any

    environment {
        PATH = "/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

    stages {

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

        stage('End To End Test') {
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

        stage('Prepare Allure Results') {
            steps {
                echo '===== PREPARING ALLURE RESULTS ====='

                sh '''
                    rm -rf allure-results-all
                    mkdir -p allure-results-all

                    cp -R allure-results-smoke/. allure-results-all/
                    cp -R allure-results-regression/. allure-results-all/
                    cp -R allure-results-e2e/. allure-results-all/
                '''
            }
        }

        stage('Allure Report') {
            steps {
                echo '===== GENERATING ALLURE REPORT ====='

                allure(
                    includeProperties: false,
                    jdk: '',
                    commandline: 'Allure',
                    results: [
                        [path: 'allure-results-all']
                    ]
                )
            }
        }
    }

    post {

        success {
            echo '========================================'
            echo '✅ PIPELINE SUCCESS'
            echo '✅ Smoke + Regression + E2E completed'
            echo '========================================'
        }

        failure {
            echo '========================================'
            echo '❌ PIPELINE FAILED'
            echo '❌ Console Output-u yoxla'
            echo '========================================'
        }

        always {
            echo 'Pipeline finished.'
        }
    }
}

