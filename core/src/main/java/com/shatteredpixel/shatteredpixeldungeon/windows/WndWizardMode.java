package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.HeroSelectScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.OptionSlider;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class WndWizardMode extends Window {

    private static final int WIDTH = 120;
    private static final int TTL_HEIGHT = 16;

    private OptionSlider expSlider;

    public WndWizardMode() {
        super();

        RenderedTextBlock title = PixelScene.renderTextBlock(Messages.get(HeroSelectScene.class, "wizard_mode"), 12);
        title.hardlight(TITLE_COLOR);
        title.setPos((WIDTH - title.width()) / 2, (TTL_HEIGHT - title.height()) / 2);
        PixelScene.align(title);
        add(title);

        int currentVal = 0;
        int multiplier = SPDSettings.wizardExpMultiplier();
        if (multiplier == 2)
            currentVal = 1;
        else if (multiplier == 4)
            currentVal = 2;
        else if (multiplier == 8)
            currentVal = 3;

        expSlider = new OptionSlider(
                Messages.get(this, "exp_title"),
                "1x", "8x",
                0, 3) {
            @Override
            protected void onChange() {
                int val = getSelectedValue();
                int multiplier = (int) Math.pow(2, val);
                SPDSettings.wizardExpMultiplier(multiplier);
            }
        };
        expSlider.setSelectedValue(currentVal);
        expSlider.setRect(0, TTL_HEIGHT + 2, WIDTH, 30);
        add(expSlider);

        resize(WIDTH, (int) expSlider.bottom());
    }
}
