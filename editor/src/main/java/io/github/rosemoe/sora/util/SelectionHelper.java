/*
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/CodeEditor
 *    Copyright (C) 2020-2021  Rosemoe
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 *
 *     Please contact Rosemoe by email 2073412493@qq.com if you need
 *     additional information or have any questions
 */
package io.github.rosemoe.sora.util;

import android.text.DynamicLayout;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.TextPaint;

/**
 * Helper class for indirectly calling Paint#getTextRunCursor(), which is
 * responsible for cursor controlling.
 *
 * @author Rosemoe
 */
public class SelectionHelper {

    private final Editable text = Editable.Factory.getInstance().newEditable("");
    private final DynamicLayout layout;

    public SelectionHelper() {
        layout = new DynamicLayout(text, new TextPaint(), Integer.MAX_VALUE / 2, Layout.Alignment.ALIGN_CENTER, 0, 0 , true);
    }

    public int moveLeft(int offset, CharSequence s) {
        int left = Math.max(0, offset - 20);
        int index = offset - left;
        text.append(s, left, Math.min(s.length(), offset + 20));
        Selection.setSelection(text, index);
        Selection.moveLeft(text, layout);
        index = Selection.getSelectionStart(text);
        text.clear();
        Selection.removeSelection(text);
        return left + index;
    }

    public int moveRight(int offset, CharSequence s) {
        int left = Math.max(0, offset - 20);
        int index = offset - left;
        text.append(s, left, Math.min(s.length(), offset + 20));
        Selection.setSelection(text, index);
        Selection.moveRight(text, layout);
        index = Selection.getSelectionStart(text);
        text.clear();
        Selection.removeSelection(text);
        return left + index;
    }

}
