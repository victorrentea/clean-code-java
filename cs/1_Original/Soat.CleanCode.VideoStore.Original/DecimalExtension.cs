using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Soat.CleanCode.VideoStore.Original
{
    public static class DecimalExtension
    {
        public static string ToOneDecimalString(this decimal thisAmount)
        {
            return thisAmount.ToString("0.0", CultureInfo.InvariantCulture);
        }
    }
}
