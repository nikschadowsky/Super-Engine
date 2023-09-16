package nikschadowsky.engine.window;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * File created on 20.08.2023
 */
public class SuperWindowFrameTest {

    @Test
    void testValidateComponentHierarchyDirectParent() {

        SuperWindowFrameWrapper superWindowFrameWrapper = new SuperWindowFrameWrapper(false);

        assertTrue(superWindowFrameWrapper.validateComponentHierarchy());
    }

    @Test
    void testValidateComponentHierarchyNested() {
        SuperWindowFrameWrapper superWindowFrameWrapper = new SuperWindowFrameWrapper(true);

        assertTrue(superWindowFrameWrapper.validateComponentHierarchy());
    }

    @Test
    void testValidateComponentHierarchyNotConnected() {
        SuperWindowFrameWrapper superWindowFrameWrapper = new SuperWindowFrameWrapper();

        assertThrowsExactly(SuperWindowInitializationException.class, superWindowFrameWrapper::validateComponentHierarchy);
    }

    @Test
    void testValidateComponentHierarchyContextHasChild() {
        SuperWindowFrameWrapper superWindowFrameWrapper = new SuperWindowFrameWrapper(0);

        assertThrowsExactly(SuperWindowInitializationException.class, superWindowFrameWrapper::validateComponentHierarchy);
    }

    private static class SuperWindowFrameWrapper extends SuperWindowFrame {

        JPanel middle;

        public SuperWindowFrameWrapper(boolean useMiddle) {

            Container root = getFrameRootContainer();

            if (useMiddle) {
                middle = new JPanel();

                root.add(middle);
                middle.add(getRenderingContainer());
            } else {
                root.add(getRenderingContainer());
            }

        }

        public SuperWindowFrameWrapper() {
        }

        public SuperWindowFrameWrapper(int dummy) {
            getRenderingContainer().add(new JPanel());
        }
    }
}
