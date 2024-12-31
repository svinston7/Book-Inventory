import { Routes } from '@angular/router';
import { IndexComponent } from '../component/index/index.component';
import { LoginComponent } from '../component/userAuth/login/login.component';
import { RegisterComponent } from '../component/userAuth/register/register.component';
import { NavbarComponent } from '../component/navbar/navbar.component';
import { ViewBookComponent } from '../component/book-showcase/view-book/view-book.component';

import { PublisherComponent } from '../component/publisher/publisher.component';


import { AuthorViewComponent } from '../component/author-view/author-view.component';
import { BookConditionComponent } from '../component/book-condition/book-condition.component';
import { ProfileComponent } from '../component/profile/profile.component';
import { AdminComponent } from '../component/admin/admin.component';
import { AuthGuard } from '../guard/auth.guard';
import { ShoppingcartComponent } from '../component/shoppingcart/shoppingcart.component';



export const routes: Routes = [
    {path:'',component:IndexComponent},
    {path:'login',component:LoginComponent},
    {path:'register',component:RegisterComponent},
    {path:'home',component:NavbarComponent,canActivate:[AuthGuard]},
    {path:'viewbook/:isbn',component:ViewBookComponent,canActivate:[AuthGuard]},
    {path:'authors',component:AuthorViewComponent,canActivate:[AuthGuard]},
    {path:'bookcondition',component:BookConditionComponent,canActivate:[AuthGuard]},
    {path:'profile',component:ProfileComponent,canActivate:[AuthGuard]},
    {path:'addbook',component:PublisherComponent,canActivate:[AuthGuard]},  
    {path:'admin',component:AdminComponent,canActivate:[AuthGuard]},
    {path:'shoppingcart',component:ShoppingcartComponent}
    // { path: '**', redirectTo: '/login' }
  
];
