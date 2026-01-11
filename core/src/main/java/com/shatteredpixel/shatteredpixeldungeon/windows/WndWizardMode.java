package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.HeroSelectScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.CheckBox;
import com.shatteredpixel.shatteredpixeldungeon.ui.OptionSlider;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class WndWizardMode extends Window {

    private static final int WIDTH = 120;
    private static final int TTL_HEIGHT = 16;
    private static final int SLIDER_HEIGHT = 30;
    private static final int BTN_HEIGHT = 18;
    private static final int GAP = 2;

    public WndWizardMode() {
        super();

        RenderedTextBlock title = PixelScene.renderTextBlock(Messages.get(HeroSelectScene.class, "wizard_mode"), 12);
        title.hardlight(TITLE_COLOR);
        title.setPos((WIDTH - title.width()) / 2, (TTL_HEIGHT - title.height()) / 2);
        PixelScene.align(title);
        add(title);

        float pos = TTL_HEIGHT + GAP;

        // Wizard Mode Enabled Checkbox
        CheckBox wizardModeEnabledCB = new CheckBox(Messages.get(this, "wizard_mode_enabled")) {
            @Override
            protected void onClick() {
                super.onClick();
                SPDSettings.wizardModeEnabled(checked());
            }
        };
        wizardModeEnabledCB.checked(SPDSettings.wizardModeEnabled());
        wizardModeEnabledCB.setRect(0, pos, WIDTH, BTN_HEIGHT);
        add(wizardModeEnabledCB);
        pos = wizardModeEnabledCB.bottom() + GAP;

        // Auto Identify Items Checkbox
        CheckBox autoIdentifyCB = new CheckBox(Messages.get(this, "auto_identify")) {
            @Override
            protected void onClick() {
                super.onClick();
                SPDSettings.wizardAutoIdentify(checked());
            }
        };
        autoIdentifyCB.checked(SPDSettings.wizardAutoIdentify());
        autoIdentifyCB.setRect(0, pos, WIDTH, BTN_HEIGHT);
        add(autoIdentifyCB);
        pos = autoIdentifyCB.bottom() + GAP;

        // Experience Multiplier Slider
        int expCurrentVal = valToIndex(SPDSettings.wizardExpMultiplier());
        OptionSlider expSlider = new OptionSlider(
                Messages.get(this, "exp_title"),
                "1x", "8x",
                0, 3) {
            @Override
            protected void onChange() {
                int multiplier = (int) Math.pow(2, getSelectedValue());
                SPDSettings.wizardExpMultiplier(multiplier);
            }
        };
        expSlider.setSelectedValue(expCurrentVal);
        expSlider.setRect(0, pos, WIDTH, SLIDER_HEIGHT);
        add(expSlider);
        pos = expSlider.bottom() + GAP;

        // Move Speed Multiplier Slider
        int moveCurrentVal = valToIndex(SPDSettings.wizardMoveSpeedMultiplier());
        OptionSlider moveSlider = new OptionSlider(
                Messages.get(this, "move_speed_title"),
                "1x", "4x",
                0, 2) {
            @Override
            protected void onChange() {
                int multiplier = (int) Math.pow(2, getSelectedValue());
                SPDSettings.wizardMoveSpeedMultiplier(multiplier);
            }
        };
        moveSlider.setSelectedValue(moveCurrentVal);
        moveSlider.setRect(0, pos, WIDTH, SLIDER_HEIGHT);
        add(moveSlider);
        pos = moveSlider.bottom() + GAP;

        // Attack Speed Multiplier Slider
        int attackCurrentVal = valToIndex(SPDSettings.wizardAttackSpeedMultiplier());
        OptionSlider attackSlider = new OptionSlider(
                Messages.get(this, "attack_speed_title"),
                "1x", "4x",
                0, 2) {
            @Override
            protected void onChange() {
                int multiplier = (int) Math.pow(2, getSelectedValue());
                SPDSettings.wizardAttackSpeedMultiplier(multiplier);
            }
        };
        attackSlider.setSelectedValue(attackCurrentVal);
        attackSlider.setRect(0, pos, WIDTH, SLIDER_HEIGHT);
        add(attackSlider);
        pos = attackSlider.bottom();

        resize(WIDTH, (int) pos);
    }

    // Convert multiplier value (1, 2, 4, 8) to slider index (0, 1, 2, 3)
    private int valToIndex(int val) {
        if (val == 2)
            return 1;
        else if (val == 4)
            return 2;
        else if (val == 8)
            return 3;
        return 0;
    }
}
