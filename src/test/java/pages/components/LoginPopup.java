package pages.components;

import elements.Button;
import elements.TextBox;
import elements.base.BaseField;
import org.openqa.selenium.By;

public class LoginPopup extends BaseField {

    public TextBox txbEmail = new TextBox(this, By.xpath(".//input[@name='email']"));
    public TextBox txbPassword = new TextBox(this, By.xpath(".//input[@name='password']"));
    public Button btnLogin = new Button(this, By.xpath(".//button[@type='submit']"));
    public Button btnClose = new Button(By.xpath("//button[@class='modal-close-button']"));

    public LoginPopup(BaseField parent, By locator) {
        super(parent, locator);
    }

    public LoginPopup(By locator) {
        super(locator);
    }
}
