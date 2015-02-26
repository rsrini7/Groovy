import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext

@Grab(group="org.apache.camel", module="camel-core", version="2.13.1")
@Grab(group="org.apache.camel", module="camel-groovy", version="2.13.1")
@Grab(group="org.apache.camel", module="camel-jetty", version="2.13.1")
@Grab(group="org.slf4j", module="slf4j-jdk14", version="1.7.5")

class MyRouteBuilder extends RouteBuilder{
	int num =0;
	def number = {++num};

	void configure(){
		from("jetty:http://0.0.0.0:8090")
			.transform({ args ->
				"You called me ${number()} times"	
			})
		  //.transform("You called me ${number()} times")

	}
}

def camelContext = new DefaultCamelContext()
camelContext.addRoutes(new MyRouteBuilder())

camelContext.start()
Thread.sleep(15000)
//System.console().readLine()
camelContext.stop()