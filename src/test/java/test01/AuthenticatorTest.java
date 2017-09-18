package test01;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

public class AuthenticatorTest {

//    @Test
//    public void testAllSuccessfulStrategyWithSuccess() {
//        login("classpath:shiro-authenticator-all-success.ini");
//        Subject subject = SecurityUtils.getSubject();
//
//        PrincipalCollection principalCollection = subject.getPrincipals();
//        /**
//    	 * Asserts that two ints are equal. If they are not
//    	 * an AssertionFailedError is thrown with the given message.
//    	 */
//        Assert.assertEquals(2, principalCollection.asList().size());
//    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:shiro-authenticator-all-fail.ini");
    }
//
//    @Test
//    public void testAtLeastOneSuccessfulStrategyWithSuccess() {
//        login("classpath:shiro-authenticator-atLeastOne-success.ini");
//        Subject subject = SecurityUtils.getSubject();
//
//        PrincipalCollection principalCollection = subject.getPrincipals();
//        Assert.assertEquals(2, principalCollection.asList().size());
//    }
//
//    @Test
//    public void testFirstOneSuccessfulStrategyWithSuccess() {
//        login("classpath:shiro-authenticator-first-success.ini");
//        Subject subject = SecurityUtils.getSubject();
//
//       PrincipalCollection principalCollection = subject.getPrincipals();
//        Assert.assertEquals(1, principalCollection.asList().size());
//    }
//
//    @Test
//    public void testAtLeastTwoStrategyWithSuccess() {
//        login("classpath:shiro-authenticator-atLeastTwo-success.ini");
//        Subject subject = SecurityUtils.getSubject();
//
//          PrincipalCollection principalCollection = subject.getPrincipals();
//        Assert.assertEquals(2, principalCollection.asList().size());
//    }
//
    @Test
    public void testOnlyOneStrategyWithSuccess() {
        login("classpath:shiro-authenticator-onlyone-success.ini");
        Subject subject = SecurityUtils.getSubject();

  PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    private void login(String configFile) {

        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);

   
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

  Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        subject.login(token);
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();}

}
