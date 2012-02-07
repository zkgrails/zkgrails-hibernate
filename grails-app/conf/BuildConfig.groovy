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
        build ("com.google.code.maven-svn-wagon:maven-svn-wagon:1.4") {
            export = false
        }
    }

    plugins {
        compile ":hibernate:$grailsVersion"
        compile ":zk:2.0.0.M2"
        build(":tomcat:$grailsVersion",
              ":release:1.0.1",
              ":svn:1.0.2") {
            export = false
        }
    }
}
