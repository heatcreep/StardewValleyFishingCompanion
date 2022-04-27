package com.example.core.utils

import com.example.core.R


fun getImageResource(image: String): Int {
    return when (image) {
        "albacore" -> R.drawable.albacore
        "anchovy" -> R.drawable.anchovy
        "angler" -> R.drawable.angler
        "blobfish" -> R.drawable.blobfish
        "blue_discus" -> R.drawable.blue_discus
        "bream" -> R.drawable.bream
        "bullhead" -> R.drawable.bullhead
        "carp" -> R.drawable.carp
        "catfish" -> R.drawable.catfish
        "chub" -> R.drawable.chub
        "crimsonfish" -> R.drawable.crimsonfish
        "dorado" -> R.drawable.dorado
        "eel" -> R.drawable.eel
        "flounder" -> R.drawable.flounder
        "ghostfish" -> R.drawable.ghostfish
        "glacierfish" -> R.drawable.glacierfish
        "glacierfish_jr" -> R.drawable.glacierfish_jr
        "halibut" -> R.drawable.halibut
        "herring" -> R.drawable.herring
        "ice_pip" -> R.drawable.ice_pip
        "largemouth_bass" -> R.drawable.largemouth_bass
        "lava_eel" -> R.drawable.lava_eel
        "legend" -> R.drawable.legend
        "legend_2" -> R.drawable.legend_2
        "lingcod" -> R.drawable.lingcod
        "lionfish" -> R.drawable.lionfish
        "midnight_carp" -> R.drawable.midnight_carp
        "midnight_squid" -> R.drawable.midnight_squid
        "ms_angler" -> R.drawable.ms_angler
        "mutant_carp" -> R.drawable.mutant_carp
        "octopus" -> R.drawable.octopus
        "perch" -> R.drawable.perch
        "pike" -> R.drawable.pike
        "pufferfish" -> R.drawable.pufferfish
        "radioactive_carp" -> R.drawable.radioactive_carp
        "rainbow_trout" -> R.drawable.rainbow_trout
        "red_mullet" -> R.drawable.red_mullet
        "red_snapper" -> R.drawable.red_snapper
        "salmon" -> R.drawable.salmon
        "sandfish" -> R.drawable.sandfish
        "sardine" -> R.drawable.sardine
        "scorpion_carp" -> R.drawable.scorpion_carp
        "sea_cucumber" -> R.drawable.sea_cucumber
        "shad" -> R.drawable.shad
        "slimejack" -> R.drawable.slimejack
        "smallmouth_bass" -> R.drawable.smallmouth_bass
        "son_of_crimsonfish" -> R.drawable.son_of_crimsonfish
        "spook_fish" -> R.drawable.spook_fish
        "squid" -> R.drawable.squid
        "stingray" -> R.drawable.stingray
        "stonefish" -> R.drawable.stonefish
        "sturgeon" -> R.drawable.sturgeon
        "sunfish" -> R.drawable.sunfish
        "super_cucumber" -> R.drawable.super_cucumber
        "tiger_trout" -> R.drawable.tiger_trout
        "tilapia" -> R.drawable.tilapia
        "tuna" -> R.drawable.tuna
        "void_salmon" -> R.drawable.void_salmon
        "walleye" -> R.drawable.walleye
        "woodskip" -> R.drawable.woodskip
        else ->
            R.drawable.junimo

    }
}
