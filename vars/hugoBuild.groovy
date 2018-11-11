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
	    agent {
	    	image 'monachus/hugo'
	    }
            stages{
                stage('checkout') {
                    steps {
                        echo 'Building..'
						checkout scm
                    }
                }
                stage('build website') {
                    steps {
                        echo 'Building..'
						sh 'hugo'
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
