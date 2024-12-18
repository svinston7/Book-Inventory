import { Routes } from '@angular/router';
import { IndexComponent } from '../component/index/index.component';
import { LoginComponent } from '../component/userAuth/login/login.component';
import { RegisterComponent } from '../component/userAuth/register/register.component';
import { HomeComponent } from '../component/home/home.component';


export const routes: Routes = [
    {path:'',component:IndexComponent},
    {path:'login',component:LoginComponent},
    {path:'register',component:RegisterComponent},
    {path:'home',component:HomeComponent}
];
