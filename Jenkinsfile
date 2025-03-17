pipeline {
    agent any

    environment {
      
        DOCKER_CREDS = credentials('dockerhub-credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                // Clones your Maven project from GitHub (branch 'master')
                git branch: 'master', url: 'https://github.com/JPLenis/Lab2COMP367.git'
            }
        }

        stage('Build Maven Project') {
            steps {
           
                bat 'mvn clean package'
            }
        }

        stage('Docker Login') {
            steps {
                script {
                    // Use the default Docker registry by leaving the first parameter empty
                    docker.withRegistry('', 'dockerhub-credentials') {
                        echo "Logged in to Docker Hub successfully."
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Build your Docker image from the Dockerfile at the root of the repo
                    // Tag it with the Jenkins build number
                    dockerImage = docker.build("jplenis/lab3:${env.BUILD_NUMBER}")
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    // Push the built image to Docker Hub
                    docker.withRegistry('', 'dockerhub-credentials') {
                        dockerImage.push()
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline execution completed."
        }
    }
}
