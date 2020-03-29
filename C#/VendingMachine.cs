using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VendingMachine
{
    public class VendingMachine
    {

        public enum State
        {
            START, INSERT, SELECT, DISPENSE, CHANGE, OFF, STOCK
        }


        public State Status { get; set; }
        public Dictionary<int, StockItem> Stock { get; private set; }
        public double Balance { get; private set; }
        public double MaxCost { get; private set; }
        public int Selection { get; private set; }

        public VendingMachine()
        {
            Stock = new Dictionary<int, StockItem>();
            loadStock();
            Status = State.START;
        }

        public void restock()
        {
            Console.WriteLine("ReStocking...");
            List<string> items = readFile();
            for (int i = 0; i < items.Count; i++)
            {
                string[] item = items[i].Split(',');
                Stock.Add(i, new StockItem(item[0],
                        Convert.ToDouble(item[1]),
                        Convert.ToInt32(item[2])));
                if (Stock[i].cost > MaxCost)
                {
                    MaxCost = Stock[i].cost;
                }
            }
            Status = State.START;
        }

        public void loadStock()
        {
            Console.WriteLine("Loading initial Stock...");
            List<string> items = readFile();
            for (int i = 0; i < items.Count; i++)
            {
                string[] item = items[i].Split(',');
                Stock.Add(i, new StockItem(item[0],
                        Convert.ToDouble(item[1]),
                        Convert.ToInt32(item[2])));
                if (Stock[i].cost > MaxCost)
                {
                    MaxCost = Stock[i].cost;
                }
            }
        }

        public List<string> readFile()
        {            
            try
            {
                List<string> result = new List<string>();
                FileStream file = new FileStream(getFileName(), FileMode.Open, FileAccess.Read);
                using (StreamReader sr = new StreamReader(file))
                {
                    string st;
                    while ((st = sr.ReadLine()) != null)
                    {
                        result.Add(st);
                    }
                }
                return result;
            }
            catch (Exception e)
            {
                return null;
            }            
        }

        public string getFileName()
        {
            Console.WriteLine("Enter the Stock's full file path: ");
            return Console.ReadLine(); 
        }

        public void insertMoney()
        {
            Console.WriteLine("Enter money (.05, .10, .25, 1): ");
            Balance +=  Convert.ToDouble(Console.ReadLine());
            Console.WriteLine("Total: $" + Balance);
            if (Balance >= MaxCost)
            {
                Status = State.SELECT;
            }
        }

        public void select()
        {
            Console.WriteLine("\nPlease select a drink, or (r)efund or (d)isplay choices: ");
            string input = Console.ReadLine();
            int choice = 0;
            try
            {
                choice = Convert.ToInt32(input);
                if (Stock[choice].count > 0)
                {
                    Status = State.DISPENSE;
                    Selection = choice;
                    return;
                }
                Console.WriteLine("Invalid Selection");
            }
            catch (Exception e)
            {
                if (input[0] == 'r')
                {
                    Status = State.CHANGE;
                }
                else if (input[0] == 'd')
                {
                    display();
                }
                else
                {
                    Console.WriteLine("Invalid Selection");
                }
            }
            Selection = -1;
        }

        private void display()
        {
            for (int i = 0; i < Stock.Count; i++)
            {
                Console.WriteLine(i + ": " + Stock[i].name + "(" + Stock[i].count + ") @$" + Stock[i].cost);
            }
            Console.WriteLine();
        }

        public void start()
        {
            Console.WriteLine("\n\nWelcome!\n");
            display();
            Console.Write("Press (e)xit, (r)eStock, or anything else to continue: ");
            string input = Console.ReadLine();
            if (input[0] == 'r')
            {
                Status = State.STOCK;
            }
            else if (input[0] == 'e')
            {
                Status = State.OFF;
            }
            else
            {
                Status = State.INSERT;
            }
        }

        public void dispenseSelection()
        {
            Console.WriteLine("Dispensing your " + Stock[Selection].name);
            Balance -= Stock[Selection].cost;
            Stock[Selection].count--;
            if (Balance == 0)
            {
                Status = State.CHANGE;
            }
            else
            {
                Status = State.START;
            }
        }

        public void dispenseChange()
        {
            Console.WriteLine("Here is your change back!");
            Console.WriteLine("Dispensed: $" + Balance);
            Balance = 0;
            Status = State.START;
        }
    }

}
