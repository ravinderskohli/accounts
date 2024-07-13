		pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn -DskipTests clean package'
		bat 'mvn jib:dockerBuild'
	     bat 'mvn clean verify sonar:sonar -Dsonar.projectKey=maven-jenkins-pipeline -Dsonar.projectName=maven-jenkins-pipeline -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_b2ec25ecb70536af124342f6ae46e442f0511e9f'

            }
   
        }
	        
        
    }
}
