import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { InventoryComponent } from './inventory/inventory.component';
import { UserComponent } from './users/users.component';

import { PurchaseLogComponent } from './purchaselog/purchaselog.component';
import { RoleComponent } from './roles/roles.component';


const routes: Routes = [
  { path: 'admin', component: AdminComponent, 
    children: [
      { path: 'inventory', component: InventoryComponent },
      { path: 'users', component: UserComponent },
      { path: 'roles', component: RoleComponent },
      { path: 'purchaselog', component: PurchaseLogComponent },

      
    ] 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
