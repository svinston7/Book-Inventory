import { Routes } from '@angular/router';
import { IndexComponent } from '../component/index/index.component';
import { LoginComponent } from '../component/userAuth/login/login.component';
import { RegisterComponent } from '../component/userAuth/register/register.component';


export const routes: Routes = [
    {path:'index',component:IndexComponent},
    {path:'login',component:LoginComponent},
    {path:'register',component:RegisterComponent}
];
