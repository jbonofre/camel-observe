package net.nanthrax.examples.camelobserve.runtime.karaf;

import org.apache.camel.CamelContext;
import org.apache.camel.karaf.core.OsgiClassResolver;
import org.apache.camel.karaf.core.OsgiDefaultCamelContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import net.nanthrax.examples.camelobserve.routes.triggerlog.TriggerLogRoute;

@Component(immediate = true, name = "camel-observe")
public class CamelComponent {

    private ServiceRegistration<CamelContext> serviceRegistration;
    private CamelContext camelContext;

    @Activate
    public void activate(ComponentContext componentContext) throws Exception {
        BundleContext bundleContext = componentContext.getBundleContext();
        OsgiDefaultCamelContext camelContext = new OsgiDefaultCamelContext(bundleContext);
        camelContext.setClassResolver(new OsgiClassResolver(camelContext, bundleContext));
        camelContext.addRoutes(new TriggerLogRoute());
        camelContext.start();
        serviceRegistration = bundleContext.registerService(CamelContext.class, camelContext, null);
    }

    @Deactivate
    public void deactivate(ComponentContext componentContext) {
        serviceRegistration.unregister();
        camelContext.stop();
    }
    
}
