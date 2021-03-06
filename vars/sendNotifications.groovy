/**
 * Send notifications based on build status string
 */
def call(String buildStatus = 'STARTED', String channel = '#ci-cd') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
      color = 'YELLOW'
      colorCode = '#FFFF00'
  } else if (buildStatus == 'SUCCESSFUL') {
      color = 'GREEN'
      colorCode = '#00FF00'
  } else if (buildStatus == 'SUCCESS') {
      color = 'GREEN'
      colorCode = '#00FF00'
  } else if (buildStatus == 'TESTS SUCCESSFUL') {
      color = 'GREEN'
      colorCode = '#00FF00'
  } else if (buildStatus == 'Deploying') {
      color = 'YELLOW'
      colorCode = '#FFFF00'
  } else if (buildStatus == 'Publishing') {
      color = 'YELLOW'
      colorCode = '#FFFF00'
  } else {
    color = 'RED'
      colorCode = '#FF0000'
  }

  // Send notifications
  slackSend (color: colorCode, message: summary, channel: channel)
}
