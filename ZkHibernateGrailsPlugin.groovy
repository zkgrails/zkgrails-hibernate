import org.zkoss.zkgrails.*
import org.zkoss.zkgrails.scaffolding.DefaultScaffoldingTemplate
import grails.util.*

class ZkHibernateGrailsPlugin {
    // the plugin version
    def version = "1.0.2-CL"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2 > *"
    // the other plugins this plugin depends on
    def dependsOn = [hibernate:GrailsUtil.grailsVersion, zk:version]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/conf/Config.groovy",
        "grails-app/conf/BuildConfig.groovy",
        "grails-app/conf/SeleniumConfig.groovy",
        "grails-app/domain/zk/**",
        "grails-app/comets/**",
        "grails-app/controllers/zk/**",
        "grails-app/composers/**",
        "grails-app/facade/**",
        "grails-app/views/**",
        "grails-app/taglib/MyTagLib.groovy",
        "grails-app/i18n/*.properties",
        "web-app/css/**",
        "web-app/issue*",
        "web-app/js/**",
        "web-app/META-INF/**",
        "web-app/test/**",
        "web-app/WEB-INF/**",
        "web-app/images/skin/**",
        "web-app/images/*.ico",
        "web-app/images/grails_*",
        "web-app/images/leftnav_*",
        "web-app/images/sp*",
        "web-app/*.zul",
        "test/**"
    ]
    def loadAfter = ['hibernate', 'zk']

    // TODO Fill in these fields
    def author = "Chanwit Kaewkasi"
    def authorEmail = "chanwit@gmail.com"
    def title = "Hibernate support for ZKGrails"
    def description = '''\\
This is the ZK/Hibernate sub-plugin for Grails.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/zk-hibernate"

    def doWithApplicationContext = { applicationContext ->
    }

    def doWithSpring = {
        if(!parentCtx?.containsBean("zkgrailsScaffoldingTemplate")) {
            zkgrailsScaffoldingTemplate(DefaultScaffoldingTemplate.class) { bean ->
                bean.scope = "prototype"
                bean.autowire = "byName"
            }
        }
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    static final String GOSIV_CLASS =
        "org.codehaus.groovy.grails.orm.hibernate.support.GrailsOpenSessionInViewFilter"

    def doWithWebDescriptor = { xml ->
        //
        // e.g. ["zul"]
        //
        def supportExts = ZkConfigHelper.supportExtensions

        //
        // e.g. ["*.zul", "/zkau/*"]
        //
        def filterUrls = supportExts.collect{ "*." + it } + ["/zkau/*"]

        //
        // e.g. ["*.zul", "*.dsp", "*.zhtml", "*.svg", "*.xml2html"]
        //
        def urls = supportExts.collect{ "*." + it } +
                   ["*.dsp", "*.zhtml", "*.svg", "*.xml2html"]

        // adding GrailsOpenSessionInView
        if(manager?.hasGrailsPlugin("hibernate")) {
            def filterElements = xml.'filter'[0]
            filterElements + {
                'filter' {
                    'filter-name' ("GOSIVFilter")
                    'filter-class' (GOSIV_CLASS)
                }
            }
            // filter for each ZK urls
            def filterMappingElements = xml.'filter-mapping'[0]
            filterUrls.each {p ->
                filterMappingElements + {
                    'filter-mapping' {
                        'filter-name'("GOSIVFilter")
                        'url-pattern'("${p}")
                    }
                }
            }
        }
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
