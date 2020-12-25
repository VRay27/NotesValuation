def getDockerTag(){
        def tag = sh script: 'git rev-parse HEAD', returnStdout: true
        return tag
        }
        

pipeline{
        agent any  
        environment{
	    Docker_tag = getDockerTag()
        }
        
        stages{


              stage('Quality Gate Status Check'){

               agent {
                docker {
                image 'maven'
                args '-v $HOME/.m2:/root/.m2'
                }
               }
                  steps{
                      script{
                      withSonarQubeEnv('sonarserver') { 
                      sh "mvn sonar:sonar"
                       }
                      timeout(time: 1, unit: 'HOURS') {
                      def qg = waitForQualityGate()
                      if (qg.status != 'OK') {
                           error "Pipeline aborted due to quality gate failure: ${qg.status}"
                      }
                    }
		    sh "mvn clean install"
                  }
                }  
              }



              stage('build')
                {
              steps{
                  script{
		  sh 'cp -r ..rayv2701/cicd27/target .'
                  sh 'docker build . -t rayv2701/cicd27/target:$Docker_tag'
		  withCredentials([string(credentialsId: 'docker_password', variable: 'docker_password')]) {			    
				  sh 'docker login -u rayv2701 -p $docker_password'
				  sh 'docker push rayv2701/cicd27:$Docker_tag'
			}
                       }
                    }
                 }		
               }     	     
}
