pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {
                sh "printenv"
                sh "mvn clean compile"
            }
        }
        stage('Test') { 
            steps {
                sh "mvn test site"
            }
            
             post {
                always {
                    junit allowEmptyResults: true, testResults: '/*.xml'   
                }
            }     
        }

        
    }
}
