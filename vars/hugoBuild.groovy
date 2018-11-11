def call(body) {
  def config = [:]
  def emptyList = []
  def branchName = BRANCH_NAME
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  if(!config.numberOfBuildsToKeep) {
        config.numberOfBuildsToKeep = 3
  }


  if(!config.notifyCulpritsOnBranchFailures){
    config.notifyCulpritsOnBranchFailures = true
  }

	
    pipeline {
        agent{
            docker {
                image 'felicianotech/docker-hugo:0.50' 
                args '-v /root/.m2:/root/.m2' 
                }
            }
            stages{
                stage('Build') {
                    steps {
                        echo 'Building..'
                    }
                }
                stage('Test') {
                    steps {
                        echo 'Testing..'
                    }
                }
                stage('Deploy') {
                    steps {
                        echo 'Deploying....'
                    }
                }
            }
    }
}
