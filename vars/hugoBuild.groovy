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
	agent any
        stages{
            stage('checkout') {
                steps {
                    echo 'Building..'
		    sh "./deploy.sh"
		}
	    }
            stage('Deploy to gh-pages') {
                steps {
                    echo 'Deploying....'
	            sh 'gh-pages deploy'
                }
            }
        }
    }
 }
