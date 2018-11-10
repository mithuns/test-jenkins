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

  try 
  {
  	pipeline 
	  {
   		agent any
   		stages 
		  {
      		stage('Source') 
			  {
        		git branch: 'test', url: 'git@diyvb:repos/gradle-greetings'
         		stash name: 'test-sources', includes: 'build.gradle,/src/test'
      		  }
      		stage('Build') 
			{
         
      		}
   		}
  	}
  }finally{
  }
}
