const fs = require('fs');
const axios = require('axios');

const imports = fs.readFileSync('imports.txt', 'utf-8').split('\n').filter(Boolean);
const output = [];

(async () => {
    for (const imp of imports) {
        const parts = imp.split('.');
        const className = parts.pop();
        const packageName = parts.join('.');
        const query = `q=${packageName}&rows=5&wt=json`;
        const url = `https://search.maven.org/solrsearch/select?${query}`;

        try {
            const res = await axios.get(url);
            const docs = res.data.response.docs;
            if (docs.length > 0) {
                const doc = docs[0]; // en yüksek skorlu sonucu al
                output.push(`implementation '${doc.g}:${doc.a}:${doc.latestVersion}'`);
            } else {
                output.push(`// Not found: ${imp}`);
            }
        } catch (e) {
            output.push(`// Error fetching: ${imp}`);
        }
    }

    fs.writeFileSync('output.gradle', output.join('\n'));
})();