/*******************************************************************************
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/sora-editor
 *    Copyright (C) 2020-2024  Rosemoe
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
 ******************************************************************************/

package io.github.rosemoe.sora.langs.monarch.registery


import io.github.rosemoe.sora.langs.monarch.registery.model.ThemeModel
import io.github.rosemoe.sora.langs.monarch.registery.model.ThemeSource

object ThemeRegistry {

    private val themeChangeListeners = mutableListOf<ThemeChangeListener>()

    private val themes = mutableListOf<ThemeModel>()

    private var currentTheme: ThemeModel = ThemeModel.EMPTY

    @Throws(Exception::class)
    fun loadTheme(themeSource: ThemeSource) {
        loadTheme(themeSource, true)
    }

    @Throws(Exception::class)
    fun loadTheme(themeSource: ThemeSource, isCurrentTheme: Boolean) {
        loadTheme(
            ThemeModel(themeSource),
            isCurrentTheme
        )
    }

    @Throws(Exception::class)
    fun loadTheme(themeModel: ThemeModel) {
        loadTheme(themeModel, true)
    }

    @Synchronized
    @Throws(Exception::class)
    fun loadTheme(
        themeModel: ThemeModel,
        isCurrentTheme: Boolean
    ) {
        if (!themeModel.isLoaded) {
            themeModel.load()
        }
        val theme: ThemeModel =
            findThemeByThemeName(themeModel.getName())
        if (theme != null) {
            setTheme(theme)
            return
        }
        allThemeModel.add(themeModel)
        if (isCurrentTheme) {
            setTheme(themeModel)
        }
    }

    @Synchronized
    fun setTheme(name: String): Boolean {
        var targetModel: ThemeModel? =
            findThemeByFileName(name)

        if (targetModel != null) {
            setTheme(targetModel)
            return true
        }

        // need?
        targetModel = findThemeByThemeName(name)

        if (targetModel != null) {
            setTheme(targetModel)
            return true
        }

        return false
    }

    fun findThemeByFileName(name: String): ThemeModel? {
        return themes.firstOrNull {
            it.name == name
        }
    }

    fun findTheme(name: String): ThemeModel? {
        return themes.firstOrNull {
            it.theme.name == name
        }
    }
}

fun interface ThemeChangeListener {
    fun onChangeTheme(newTheme: ThemeModel)
}