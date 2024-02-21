package org.firstinspires.ftc.teamcode.roadrunner.util
import kotlin.math.abs
const val EPSILON = 1e-6
infix fun Double.epsilonEquals(other: Double) = abs(this - other) < EPSILON