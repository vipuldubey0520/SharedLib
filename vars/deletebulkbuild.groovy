/*** 
  "name" : "Bulk Delete Builds",
  "comment" : "For a given job and a given range of possible build numbers, delete those builds.",
  "parameters" : [ 'jobName', 'buildRange' ],
  "authors" : [
    { name : "Vipul" }
  ]
} **/




import jenkins.model.*;
import hudson.model.Fingerprint.RangeSet;
import jenkins.model.*
import hudson.model.*
import com.cloudbees.hudson.plugins.folder.*
import jenkins.branch.*
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.multibranch.*
  
//def call() {
  
//************************************************************************************************ Case 1
  // give name of the job with the range of builds you want to delete .
/***
def jobName = "Deploy"

// The range of build numbers to delete.
def buildRange = "100-107"
def j = jenkins.model.Jenkins.instance.getItem(jobName);
def r = RangeSet.fromString(buildRange, true);
j.getBuilds(r).each { it.delete() }
**/
//************************************************************************************************
  
  
//************************************************************************************************ Case 2
//this will check for all builds and directly delete all builds below the max number mentioned
/**
def jobName = "N_D_P_P"
def maxNumber = 20

Jenkins.instance.getItemByFullName(jobName).builds.findAll {
  it.number <= maxNumber
}.each {
  it.delete()
} 
**/
//************************************************************************************************
  
def deleteOldBuilds(item, Integer numberOfBuildsToKeep, Integer numberOfSuccessfulBuildsKept) {
    def count = 1

    println('Checking for Old Builds...')

    for (build in item.getBuilds()) {
        if(count++ >= numberOfBuildsToKeep) {
            if(item.getBuildStatusIconClassName() == 'icon-blue' && numberOfSuccessfulBuildsKept == 0) {
                println('Keep ' + build)
            } else {
                println('Deleting ' + build)
                build.delete()
            }
        } else if(item.getBuildStatusIconClassName() == 'icon-blue') {
            numberOfSuccessfulBuildsKept++
        }
    }
    println('PRIOR BUILD COUNT: (' + count + ')')
    println ''
}

def listJobObjects(item, Integer numberOfBuildsToKeep, Integer numberOfSuccessfulBuildsKept) {
    if(item instanceof Project) {
        println('PROJECT: (' + item.getName() + ')')
        deleteOldBuilds(item, numberOfBuildsToKeep, numberOfSuccessfulBuildsKept)
    } else if(item instanceof Folder) {
        println ''
        println('FOLDER: (' + item.getName() + ')')
        println('*************************************')
        for (subItem in item.items) {
            listJobObjects(subItem, numberOfBuildsToKeep, numberOfSuccessfulBuildsKept)
        }
    } else if(item instanceof WorkflowMultiBranchProject) {
        println('MULTIBRANCH-PROJECT: (' + item.getName() + ')')
        for (subItem in item.items) {
            listJobObjects(subItem, numberOfBuildsToKeep, numberOfSuccessfulBuildsKept)
        }
    }  else if(item instanceof WorkflowJob) {
        println('MULTIBRANCH-JOB: (' + item.getName() + ')')
        deleteOldBuilds(item, numberOfBuildsToKeep, numberOfSuccessfulBuildsKept)
    } else if(item instanceof OrganizationFolder) {
        println('ORG-FOLDER: (' + item.getName() + ')')
        for (subItem in item.items) {
            listJobObjects(subItem, numberOfBuildsToKeep, numberOfSuccessfulBuildsKept)
        }
    } else {
        println('UNKNOWN: (' + item.getName() + ')')
        println('CLASS: (' + item.getClass() + ')')
        println('INSPECT: (' + item.inspect() + ')')
    }
}

for (item in Jenkins.instance.items) {
    println ''
    listJobObjects(item, 10, 0)
    println('*************************************')
}
//}
