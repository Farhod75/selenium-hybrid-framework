pipeline {
    agent any

    parameters {
        choice(name: 'DATA_SOURCE', choices: ['csv', 'excel'], description: 'Choose dataset')
    }

    tools {
        maven 'Maven'   // Maven configured in Global Tool Config
        jdk 'JDK17'     // JDK configured in Global Tool Config
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat """
                    mvn clean test -Ddata.source=${params.DATA_SOURCE} -Dselenium.headless=true
                """
            }
        }
    }

    post {
        always {
            allure([
                results: [[path: 'target/allure-results']],
                commandline: 'Allure'
            ])
        }
    }
}