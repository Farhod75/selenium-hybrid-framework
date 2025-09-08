pipeline {
    agent any
    parameters {
        choice(name: 'DATA_SOURCE', choices: ['csv', 'excel'], description: 'Choose dataset')
    }
    stages {
        stage('Run Tests') {
            steps {
                sh """
                mvn clean test -Ddata.source=${params.DATA_SOURCE} -Dselenium.headless=true
                """
            }
        }
    }
    post {
        always {
            allure([
                path: 'target/allure-results'
            ])
        }
    }
}