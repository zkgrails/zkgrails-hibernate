grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        mavenRepo "http://zkgrails.googlecode.com/svn/repo/"
        grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenCentral()        
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        compile "org.zkoss.zk.grails:zk:5.0.9"
        build ("com.google.code.maven-svn-wagon:maven-svn-wagon:1.4") {
            export = false
        }
    }
    plugins {
        compile ":hibernate:$grailsVersion"
        build(":tomcat:$grailsVersion",
              ":release:1.0.0.RC3",
              ":svn:1.0.0.M1") {
            export = false
        }
    }
}
