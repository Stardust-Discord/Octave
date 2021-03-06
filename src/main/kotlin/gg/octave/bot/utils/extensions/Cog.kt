/*
 * MIT License
 *
 * Copyright (c) 2020 Melms Media LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.octave.bot.utils.extensions

import me.devoxin.flight.api.CommandFunction
import me.devoxin.flight.api.Context
import me.devoxin.flight.api.entities.Cog

fun Cog.DEFAULT_SUBCOMMAND(ctx: Context) {
    if (ctx.invokedCommand !is CommandFunction) { // HUH
        return
    }

    val command = ctx.invokedCommand as CommandFunction
    val subcommands = command.subcommands.values.toSet()
    val longestName = subcommands.maxByOrNull { it.name.length }?.name?.length ?: 15
    val commandList = subcommands.sortedBy { it.name }
        .joinToString("\n") { "`${it.name.padEnd(longestName)}:` ${it.properties.description}" }

    ctx.send {
        setColor(0x9570D3)
        setTitle("${command.name} | Sub-Commands")
        setDescription(commandList)
        setFooter("Example: ${ctx.trigger}${command.name} ${subcommands.first().name}")
    }
}