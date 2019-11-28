import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @author shaoyx
 * @date 10:17  2019/11/28
 */
@RunWith(Arquillian.class)
public class CgLibDynamicProxyTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(com.syx.springboot.inredis.test.aop.cglib.CgLibDynamicProxy.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
