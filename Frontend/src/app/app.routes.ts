import { Routes } from '@angular/router';
import { IndexComponent } from '../component/index/index.component';
import { LoginComponent } from '../component/userAuth/login/login.component';
import { RegisterComponent } from '../component/userAuth/register/register.component';
import { NavbarComponent } from '../component/navbar/navbar.component';
import { ViewBookComponent } from '../component/book-showcase/view-book/view-book.component';
import { AuthorViewComponent } from '../component/author-view/author-view.component';
import { PurchaseLogComponent } from '../component/admin/purchaselog/purchaselog.component';
import { RoleComponent } from '../component/admin/roles/roles.component';
import { UserComponent } from '../component/admin/users/users.component';
import { InventoryComponent } from '../component/admin/inventory/inventory.component';
import { AddbookComponent } from '../component/admin/addbook/addbook.component';
import { UpdateBookComponent } from '../component/admin/updatebook/updatebook.component';
import { DashboardComponent } from '../component/admin/dashboard/dashboard.component';

export const routes: Routes = [
    { path: '', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'home', component: NavbarComponent },
    { path: 'viewbook/:isbn', component: ViewBookComponent },
    { path: 'authors', component: AuthorViewComponent },
  
    // Admin Routes with /admin prefix
    {
      path: 'admin',
      children: [
        { path: 'dashboard', component: DashboardComponent },
        { path: 'inventory', component: InventoryComponent, children: [
            { path: 'add', component: AddbookComponent },
            { path: 'update/:id', component: UpdateBookComponent },
          ]
        },
        { path: 'users', component: UserComponent },
        { path: 'roles', component: RoleComponent },
        { path: 'reports', component: PurchaseLogComponent },
        { path: 'book', component: BookComponent },
        { path: 'category', component: CategoryComponent },
        { path: 'publisher', component: PublisherComponent },
        { path: 'purchaseLog', component: PurchaseLogComponent },
        { path: 'bookCondition', component: BookConditionComponent },
      ]
    },
  ];
