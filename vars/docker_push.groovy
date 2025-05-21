def call(String Project, String ImageTag, String dockerhubuser){
    withCredentials([usernamePassword(credentialsId: 'dockerHubCred', 
                                      passwordVariable: 'dockerHubPass', 
                                      usernameVariable: 'dockerHubUser')]) {
        sh """
            echo "Logging in to Docker Hub..."
            echo \$dockerHubPass | docker login -u \$dockerHubUser --password-stdin
            echo "Tagging image..."
            docker tag ${Project}:${ImageTag} ${dockerhubuser}/${Project}:${ImageTag}
            echo "Pushing image to Docker Hub..."
            docker push ${dockerhubuser}/${Project}:${ImageTag}
            echo "Logout from Docker Hub..."
            docker logout
        """
    }
}
