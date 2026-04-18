package tomato.gui.theme;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Gruvbox Dark Hard theme for Tomato.
 *
 * Installs DarculaTheme as the DarkLaf base then overrides UIManager defaults
 * with the classic Gruvbox Dark Hard palette by morhetz.
 *
 * Usage:
 *   GruvboxTheme.install();
 *   // optionally apply the best available monospace font:
 *   TomatoGUI.fontNameTextAreas(GruvboxTheme.bestMonoFont(), Font.PLAIN);
 */
public class GruvboxTheme {

    // ── Gruvbox Dark Hard palette ──────────────────────────────────────────────
    private static final Color BG_HARD  = new Color(0x1d, 0x20, 0x21);
    private static final Color BG       = new Color(0x28, 0x28, 0x28);
    private static final Color BG1      = new Color(0x3c, 0x38, 0x36);
    private static final Color BG2      = new Color(0x50, 0x49, 0x45);
    private static final Color BG3      = new Color(0x66, 0x5c, 0x54);
    private static final Color BG4      = new Color(0x7c, 0x6f, 0x64);

    private static final Color FG       = new Color(0xeb, 0xdb, 0xb2);
    private static final Color FG1      = new Color(0xd5, 0xc4, 0xa1);
    private static final Color FG2      = new Color(0xbd, 0xae, 0x93);
    private static final Color FG3      = new Color(0xa8, 0x99, 0x84);

    private static final Color YELLOW_B = new Color(0xfa, 0xbd, 0x2f);
    private static final Color BLUE     = new Color(0x45, 0x85, 0x88);
    private static final Color BLUE_B   = new Color(0x83, 0xa5, 0x98);
    private static final Color GREEN_B  = new Color(0xb8, 0xbb, 0x26);
    private static final Color RED_B    = new Color(0xfb, 0x49, 0x34);
    private static final Color ORANGE_B = new Color(0xfe, 0x80, 0x19);
    private static final Color AQUA_B   = new Color(0x8e, 0xc0, 0x7c);
    // ──────────────────────────────────────────────────────────────────────────

    /**
     * Installs the Gruvbox Dark Hard theme.
     * Call this on the Swing EDT before (or after) creating any windows;
     * if called while windows are visible you may need to repaint them.
     */
    public static void install() {
        // Use DarculaTheme as the DarkLaf structural base (window decorations,
        // icons, focus rings, etc.) then paint every colour with Gruvbox.
        LafManager.install(new DarculaTheme());

        UIDefaults ui = UIManager.getDefaults();

        // ── Main surfaces ───────────────────────────────────────────────────
        String[] bgPanelKeys = {
            "Panel.background", "OptionPane.background",
            "ScrollPane.background", "Viewport.background",
            "TabbedPane.background", "SplitPane.background",
            "ToolBar.background", "PopupMenu.background",
            "SplitPaneDivider.background",
        };
        for (Object k : bgPanelKeys) ui.put(k, BG);

        ui.put("Panel.foreground",      FG);
        ui.put("OptionPane.foreground", FG);

        // ── Tool-tips ───────────────────────────────────────────────────────
        ui.put("ToolTip.background", BG2);
        ui.put("ToolTip.foreground", FG1);

        // ── Text components ─────────────────────────────────────────────────
        String[] textPrefixes = {
            "TextArea", "TextPane", "EditorPane",
            "TextField", "FormattedTextField", "PasswordField",
        };
        for (String p : textPrefixes) {
            ui.put(p + ".background",          BG_HARD);
            ui.put(p + ".foreground",          FG);
            ui.put(p + ".caretForeground",     YELLOW_B);
            ui.put(p + ".selectionBackground", BLUE);
            ui.put(p + ".selectionForeground", FG);
            ui.put(p + ".inactiveForeground",  FG3);
        }

        // ── Buttons ─────────────────────────────────────────────────────────
        ui.put("Button.background",       BG2);
        ui.put("Button.foreground",       FG);
        ui.put("Button.select",           BG3);
        ui.put("ToggleButton.background", BG2);
        ui.put("ToggleButton.foreground", FG);

        // ── Menus ────────────────────────────────────────────────────────────
        String[] menuPrefixes = {
            "MenuBar", "Menu", "MenuItem",
            "CheckBoxMenuItem", "RadioButtonMenuItem", "PopupMenu",
        };
        for (String p : menuPrefixes) {
            ui.put(p + ".background",          BG);
            ui.put(p + ".foreground",          FG);
            ui.put(p + ".selectionBackground", BG2);
            ui.put(p + ".selectionForeground", YELLOW_B);
            ui.put(p + ".acceleratorForeground", FG2);
        }
        // MenuBar needs explicit override so the bar itself looks right.
        ui.put("MenuBar.background", BG);
        ui.put("MenuBar.foreground", FG);

        // ── Tabbed pane ──────────────────────────────────────────────────────
        ui.put("TabbedPane.foreground",           FG);
        ui.put("TabbedPane.selected",             BG1);
        ui.put("TabbedPane.selectedForeground",   YELLOW_B);
        ui.put("TabbedPane.unselectedBackground", BG);
        ui.put("TabbedPane.highlight",            BG2);
        ui.put("TabbedPane.darkShadow",           BG_HARD);

        // ── Lists & tables ───────────────────────────────────────────────────
        ui.put("List.background",           BG_HARD);
        ui.put("List.foreground",           FG);
        ui.put("List.selectionBackground",  BLUE);
        ui.put("List.selectionForeground",  FG);
        ui.put("List.dropCellBackground",   BG2);

        ui.put("Table.background",          BG_HARD);
        ui.put("Table.foreground",          FG);
        ui.put("Table.selectionBackground", BLUE);
        ui.put("Table.selectionForeground", FG);
        ui.put("Table.gridColor",           BG2);
        ui.put("Table.focusCellBackground", BG1);
        ui.put("Table.focusCellForeground", FG);
        ui.put("TableHeader.background",    BG1);
        ui.put("TableHeader.foreground",    FG1);

        // ── Trees ────────────────────────────────────────────────────────────
        ui.put("Tree.background",           BG_HARD);
        ui.put("Tree.foreground",           FG);
        ui.put("Tree.selectionBackground",  BLUE);
        ui.put("Tree.selectionForeground",  FG);
        ui.put("Tree.textBackground",       BG_HARD);
        ui.put("Tree.textForeground",       FG);

        // ── Scroll bars ──────────────────────────────────────────────────────
        ui.put("ScrollBar.background",     BG1);
        ui.put("ScrollBar.thumb",          BG3);
        ui.put("ScrollBar.thumbHighlight", BG4);
        ui.put("ScrollBar.track",          BG1);
        ui.put("ScrollBar.trackHighlight", BG2);

        // ── Labels ──────────────────────────────────────────────────────────
        ui.put("Label.foreground",          FG);
        ui.put("Label.disabledForeground",  FG3);

        // ── Check / radio ────────────────────────────────────────────────────
        ui.put("CheckBox.background",    BG);
        ui.put("CheckBox.foreground",    FG);
        ui.put("RadioButton.background", BG);
        ui.put("RadioButton.foreground", FG);

        // ── Combo box ────────────────────────────────────────────────────────
        ui.put("ComboBox.background",          BG1);
        ui.put("ComboBox.foreground",          FG);
        ui.put("ComboBox.selectionBackground", BLUE);
        ui.put("ComboBox.selectionForeground", FG);

        // ── Progress bar & slider ────────────────────────────────────────────
        ui.put("ProgressBar.background", BG2);
        ui.put("ProgressBar.foreground", AQUA_B);
        ui.put("Slider.background",      BG);
        ui.put("Slider.foreground",      YELLOW_B);

        // ── Separators ──────────────────────────────────────────────────────
        ui.put("Separator.foreground", BG2);
        ui.put("Separator.background", BG2);

        // ── Spinner ──────────────────────────────────────────────────────────
        ui.put("Spinner.background", BG1);
        ui.put("Spinner.foreground", FG);

        // ── Force all live windows to re-read the updated UIManager values.
        // LafManager.install() fired updateComponentTreeUI for the DarculaTheme
        // values; we need a second pass now that our overrides are in place.
        SwingUtilities.invokeLater(() -> {
            for (Window w : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(w);
                w.repaint();
            }
        });
    }

    /**
     * Returns the name of the best monospaced font available on this system.
     * Priority: JetBrains Mono → Cascadia Code → Cascadia Mono → Consolas → Monospaced
     */
    public static String bestMonoFont() {
        String[] preferred = {
            "JetBrains Mono", "Cascadia Code", "Cascadia Mono", "Consolas",
        };
        Set<String> installed = new HashSet<>();
        for (String name : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            installed.add(name);
        }
        for (String f : preferred) {
            if (installed.contains(f)) return f;
        }
        return Font.MONOSPACED;
    }
}
