import { Routes } from '@angular/router';
import { IndexComponent } from '../component/index/index.component';
import { LoginComponent } from '../component/userAuth/login/login.component';
import { RegisterComponent } from '../component/userAuth/register/register.component';
import { HomeComponent } from '../component/home/home.component';
import { NavbarComponent } from '../component/navbar/navbar.component';
import { ViewBookComponent } from '../component/book-showcase/view-book/view-book.component';
import { ShoppingcartComponent } from '../component/shoppingcart/shoppingcart.component';


export const routes: Routes = [
    {path:'',component:IndexComponent},
    {path:'login',component:LoginComponent},
    {path:'register',component:RegisterComponent},
    {path:'home',component:NavbarComponent},
    {path:'viewbook/:isbn',component:ViewBookComponent},
    {path:'shoppingcart',component:ShoppingcartComponent}
];
