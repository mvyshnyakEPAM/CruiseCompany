package ua.training.controller.util;

import org.junit.Assert;
import org.junit.Test;
import ua.training.constants.Pages;
import ua.training.constants.URLs;
import ua.training.model.entities.User;

import java.util.HashMap;

/**
 * Максим
 * 13.05.2018
 */
public class ControllerUtilTest {

    @Test
    public void test_getUserPage_client_page_for_client() {
        String page = ControllerUtil.getUserPage(User.Role.CLIENT);
        Assert.assertTrue(page.equals(Pages.CLIENT));
    }

    @Test
    public void test_getUserPage_admin_page_for_admin() {
        String page = ControllerUtil.getUserPage(User.Role.ADMIN);
        Assert.assertTrue(page.equals(Pages.ADMIN));
    }

    @Test
    public void test_getUserPage_admin_page_for_client() {
        String page = ControllerUtil.getUserPage(User.Role.CLIENT);
        Assert.assertFalse(page.equals(Pages.ADMIN));
    }

    @Test
    public void test_getUserPage_client_page_for_admin() {
        String page = ControllerUtil.getUserPage(User.Role.ADMIN);
        Assert.assertFalse(page.equals(Pages.CLIENT));
    }

    @Test
    public void test_getRedirectPath_index_uri_for_guest() {
        String page = ControllerUtil.getRedirectPath(User.Role.GUEST);
        Assert.assertTrue(page.equals(URLs.INDEX));
    }

    @Test
    public void test_getRedirectPath_main_uri_for_client() {
        String page = ControllerUtil.getRedirectPath(User.Role.CLIENT);
        Assert.assertTrue(page.equals(URLs.MAIN));
    }

    @Test
    public void test_getRedirectPath_main_uri_for_admin() {
        String page = ControllerUtil.getRedirectPath(User.Role.ADMIN);
        Assert.assertTrue(page.equals(URLs.MAIN));
    }

    @Test
    public void test_getRedirectPath_main_uri_for_guest() {
        String page = ControllerUtil.getRedirectPath(User.Role.GUEST);
        Assert.assertFalse(page.equals(URLs.MAIN));
    }

    @Test
    public void test_getRedirectPath_index_uri_for_client() {
        String page = ControllerUtil.getRedirectPath(User.Role.CLIENT);
        Assert.assertFalse(page.equals(URLs.INDEX));
    }

    @Test
    public void test_getRedirectPath_index_uri_for_admin() {
        String page = ControllerUtil.getRedirectPath(User.Role.ADMIN);
        Assert.assertFalse(page.equals(URLs.INDEX));
    }

    @Test
    public void test_isDataValid_login_is_null() {
        Assert.assertFalse(ControllerUtil.isDataValid(new HashMap<>(), null, "123"));
    }

    @Test
    public void test_isDataValid_password_is_null() {
        Assert.assertFalse(ControllerUtil.isDataValid(new HashMap<>(), "user", null));
    }

    @Test
    public void test_isDataValid_login_and_password_are_null() {
        Assert.assertFalse(ControllerUtil.isDataValid(new HashMap<>(), null, null));
    }

    @Test
    public void test_isDataValid_login_and_password_are_valid() {
        Assert.assertTrue(ControllerUtil.isDataValid(new HashMap<>(), "user", "123"));
    }

    @Test
    public void test_isDataValid_login_invalid() {
        Assert.assertFalse(ControllerUtil.isDataValid(new HashMap<>(), "us", "123"));
    }

    @Test
    public void test_isDataValid_password_invalid() {
        Assert.assertFalse(ControllerUtil.isDataValid(new HashMap<>(), "user", "12"));
    }

    @Test
    public void test_isDataValid_login_and_password_invalid() {
        Assert.assertFalse(ControllerUtil.isDataValid(new HashMap<>(), "us", "12"));
    }
}
