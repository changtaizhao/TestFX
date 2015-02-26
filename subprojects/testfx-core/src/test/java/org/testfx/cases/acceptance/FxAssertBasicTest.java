/*
 * Copyright 2013-2014 SmartBear Software
 * Copyright 2014-2015 The TestFX Contributors
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
 * European Commission - subsequent versions of the EUPL (the "Licence"); You may
 * not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 * http://ec.europa.eu/idabc/eupl
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */
package org.testfx.cases.acceptance;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.Test;
import org.testfx.cases.TestCaseBase;

import static org.hamcrest.Matchers.not;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.api.FxToolkit.setupApplication;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class FxAssertBasicTest extends TestCaseBase {

    //---------------------------------------------------------------------------------------------
    // FIXTURE METHODS.
    //---------------------------------------------------------------------------------------------

    @Before
    public void setup() throws Exception {
        setupApplication(DemoApplication.class);
    }

    //---------------------------------------------------------------------------------------------
    // FEATURE METHODS.
    //---------------------------------------------------------------------------------------------

    @Test
    public void missing_is_null() {
        // expect:
        verifyThat("#missing", isNull());
    }

    @Test
    public void button_is_not_null() {
        // expect:
        verifyThat("#button", isNotNull());
    }

    @Test
    public void button_is_enabled() {
        // expect:
        verifyThat("#button", isEnabled());
    }

    @Test
    public void button_is_disabled() {
        // when:
        interact(() -> {
            nodes("#button").queryFirst().setDisable(true);
        });

        // then:
        verifyThat("#button", isDisabled());
    }

    @Test
    public void button_is_visible() {
        // expect:
        verifyThat("#button", isVisible());
    }

    @Test
    public void button_is_invisible() {
        // when:
        interact(() -> {
            nodes("#button").queryFirst().setVisible(false);
        });

        // then:
        verifyThat("#button", isInvisible());
    }

    @Test
    public void button_has_label() {
        // when:
        clickOn("#button");

        // then:
        verifyThat("#button", hasText("clicked!"));
    }

    @Test
    public void button_has_not_label() {
        // expect:
        verifyThat("#button", not(hasText("clicked!")));
    }

    //@Test
    //public void foo() {
    //    verifyThat("#button", nodeMatcher(TreeView.class, "is true", (TreeView input) -> true));
    //}

    //---------------------------------------------------------------------------------------------
    // TEST FIXTURES.
    //---------------------------------------------------------------------------------------------

    public static class DemoApplication extends Application {
        @Override
        public void start(Stage stage) {
            Button button = new Button("click me!");
            button.setId("button");
            button.setOnAction(actionEvent -> button.setText("clicked!"));
            Scene scene = new Scene(button, 600, 400);
            stage.setScene(scene);
            stage.setTitle(getClass().getSimpleName());
            stage.show();
        }
    }

}
