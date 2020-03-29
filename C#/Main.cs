using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VendingMachine
{
    public static class MainClass
    {
        public static void Main(string[] args)
        {
            run();
        }
        private static void run()
        {
            VendingMachine vend = new VendingMachine();
            while (vend.Status != VendingMachine.State.OFF)
            {
                if (vend.Status == VendingMachine.State.START)
                {
                    vend.start();
                }
                if (vend.Status == VendingMachine.State.INSERT)
                {
                    vend.insertMoney();
                }
                if (vend.Status == VendingMachine.State.SELECT)
                {
                    vend.select();
                }
                if (vend.Status == VendingMachine.State.DISPENSE)
                {
                    vend.dispenseSelection();
                }
                if (vend.Status == VendingMachine.State.CHANGE)
                {
                    vend.dispenseChange();
                }
                if (vend.Status == VendingMachine.State.STOCK)
                {
                    vend.restock();
                }
            }
        }
    }
}
