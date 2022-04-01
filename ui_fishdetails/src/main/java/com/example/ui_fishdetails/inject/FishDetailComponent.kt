package com.example.ui_fishdetails.inject

import com.example.core.navigation.NavDelegate
import com.example.ui_fishdetails.FishDetailViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent
interface FishDetailComponent {
    val navDelegate: NavDelegate
    val viewModel: FishDetailViewModel

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun navigator(navDelegate: NavDelegate): Builder

        @BindsInstance
        fun fishId(@Named("fishId") fishId: Int): Builder

        fun build(): FishDetailComponent
    }
}