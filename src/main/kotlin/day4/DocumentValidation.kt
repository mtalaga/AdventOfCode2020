package day4

import java.util.regex.Pattern

class DocumentValidation (private val documentList: List<Document>) {

    fun numberOfPassports(): Int {
        var passports = 0
        for (document in documentList) {
            if (document.isPassport()) {
                passports+=1
            }
        }
        return passports
    }
}

class Document() {

    private val hairColorRegex = Pattern.compile("^#([a-f0-9]{6})\$")
    private val cmRegex = Pattern.compile("^([0-9]{3}cm)\$")
    private val inRegex = Pattern.compile("^([0-9]{2}in)\$")
    private val passportRegex = Pattern.compile("^([0-9]{9})\$")
    private val eyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    var birthYear: Int? = null
        set(value) {
            if (value != null && value in 1920..2002) field = value
        }

    var issueYear: Int? = null
        set(value) {
            if (value != null && value in 2010..2020) field = value
        }

    var expirationYear: Int? = null
        set(value) {
            if (value != null && value in 2020..2030) field = value
        }

    var height: String? = null
        set(value) {
            if (value != null) {
                if (cmRegex.matcher(value).matches()) {
                    val height = value.substring(0, 3).toInt()
                    if (height in 150..193) {
                        field = value
                    }
                }
                if (inRegex.matcher(value).matches()) {
                    val height = value.substring(0, 2).toInt()
                    if (height in 59..76) {
                        field = value
                    }
                }
            }
        }

    var hairColor: String? = null
        set(value) {
            if (value != null && hairColorRegex.matcher(value).matches()) {
                field = value
            }
        }
    var eyeColor: String? = null
        set(value) {
            if (value != null && eyeColors.contains(value)) {
                field = value
            }
        }
    var passportId: String? = null
        set(value) {
            if (value != null && passportRegex.matcher(value).matches()) {
                field = value
            }
        }
    var countryId: String? = null
        set(value) {
            if (value != null) {
                field = value
            }
        }


    constructor(properties: Map<String, String>) : this() {
        birthYear = properties["byr"]?.toInt()
        issueYear =  properties["iyr"]?.toInt()
        expirationYear = properties["eyr"]?.toInt()
        height = properties["hgt"]
        hairColor =  properties["hcl"]
        eyeColor =  properties["ecl"]
        passportId =  properties["pid"]
        countryId =  properties["cid"]
    }

    fun isPassport() : Boolean {
        return birthYear != null && issueYear != null && expirationYear != null && height != null && hairColor != null && eyeColor != null && passportId != null
    }
}