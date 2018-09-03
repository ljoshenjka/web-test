package pages.components;

import elements.Button;
import elements.TextBox;
import elements.base.BaseField;
import org.openqa.selenium.By;

public class PersonalDataPopup extends BaseField {

    public TextBox txbName = new TextBox(this, By.xpath(".//input[@name='name']"));
    public TextBox txbSurname = new TextBox(this, By.xpath(".//input[@name='surname']"));
    public Button btnUpdateProfile = new Button(this, By.xpath(".//button[@type='submit']"));
    public Button btnClose = new Button(By.xpath("//button[@class='modal-close-button']"));

    public PersonalDataPopup(BaseField parent, By locator) {
        super(parent, locator);
    }

    public PersonalDataPopup(By locator) {
        super(locator);
    }
}
