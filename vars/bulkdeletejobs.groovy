/*
   Delete all disabled FreeStyle and MatrixProject Jenkins jobs except for a
   FreeStyle job named "_jervis_generator".
 */
import hudson.matrix.MatrixProject
import hudson.model.FreeStyleProject
import hudson.model.Job
import jenkins.model.Jenkins

def call() {
Jenkins.instance.getAllItems(Job.class).findAll { Job j ->
    ( j in FreeStyleProject || j in MatrixProject ) &&
    j.fullName=='testvipul' && j.fullName=='testvipul1' && j.fullName=='testvipul2'
}.each { Job j ->
    j.delete()
    println "Deleted ${j.fullName}"
}
null
}

